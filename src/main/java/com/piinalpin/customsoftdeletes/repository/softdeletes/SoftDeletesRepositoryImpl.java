package com.piinalpin.customsoftdeletes.repository.softdeletes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("java:S119")
@Slf4j
public class SoftDeletesRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
        implements SoftDeletesRepository<T, ID> {

    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager em;
    private final Class<T> domainClass;
    private static final String DELETED_FIELD = "deletedAt";

    public SoftDeletesRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
        this.domainClass = domainClass;
        this.entityInformation = JpaEntityInformationSupport.getEntityInformation(domainClass, em);
    }

    @Override
    public List<T> findAll(){
        if (isFieldDeletedAtExists()) return super.findAll(notDeleted());
        return super.findAll();
    }

    @Override
    public List<T> findAll(Sort sort){
        if (isFieldDeletedAtExists()) return super.findAll(notDeleted(), sort);
        return super.findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable page) {
        if (isFieldDeletedAtExists()) return super.findAll(notDeleted(), page);
        return super.findAll(page);
    }

    @Override
    public Optional<T> findOne(ID id) {
        if (isFieldDeletedAtExists())
            return super.findOne(Specification.where(new ByIdSpecification<>(entityInformation, id)).and(notDeleted()));
        return super.findOne(Specification.where(new ByIdSpecification<>(entityInformation, id)));
    }

    @Override
    @Transactional
    public void delete(ID id) {
        softDelete(id, LocalDateTime.now());
    }

    @Override
    @Transactional
    public void delete(T entity) {
        softDelete(entity, LocalDateTime.now());
    }

    @Override
    public void hardDelete(T entity) {
        super.delete(entity);
    }

    private boolean isFieldDeletedAtExists() {
        try {
            domainClass.getSuperclass().getDeclaredField(DELETED_FIELD);
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    private void softDelete(ID id, LocalDateTime localDateTime) {
        Assert.notNull(id, "The given id must not be null!");

        Optional<T> entity = findOne(id);

        if (entity.isEmpty())
            throw new EmptyResultDataAccessException(
                    String.format("No %s entity with id %s exists!", entityInformation.getJavaType(), id), 1);

        softDelete(entity.get(), localDateTime);
    }

    private void softDelete(T entity, LocalDateTime localDateTime) {
        Assert.notNull(entity, "The entity must not be null!");

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaUpdate<T> update = cb.createCriteriaUpdate(domainClass);

        Root<T> root = update.from(domainClass);

        update.set(DELETED_FIELD, localDateTime);

        update.where(
                cb.equal(
                        root.<ID>get(Objects.requireNonNull(entityInformation.getIdAttribute()).getName()),
                        entityInformation.getId(entity)
                )
        );

        em.createQuery(update).executeUpdate();
    }

    private static final class ByIdSpecification<T, ID> implements Specification<T> {

        private static final long serialVersionUID = 6523470832851906115L;
        private final transient JpaEntityInformation<T, ?> entityInformation;
        private final transient ID id;

        ByIdSpecification(JpaEntityInformation<T, ?> entityInformation, ID id) {
            this.entityInformation = entityInformation;
            this.id = id;
        }

        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            return cb.equal(root.<ID>get(Objects.requireNonNull(entityInformation.getIdAttribute()).getName()), id);
        }
    }

    private static final class DeletedIsNUll<T> implements Specification<T> {

        private static final long serialVersionUID = -940322276301888908L;

        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            return criteriaBuilder.isNull(root.<LocalDateTime>get(DELETED_FIELD));
        }

    }

    private static <T> Specification<T> notDeleted() {
        return Specification.where(new DeletedIsNUll<>());
    }

}
