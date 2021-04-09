package it.engineering.student.service.exception;

public class EntityNotPresent extends ApplicationException{
    private final Object entity;

    public EntityNotPresent(Object entity, String message) {
        super(message);
        this.entity = entity;
    }

    public Object getEntity() {
        return entity;
    }
}
