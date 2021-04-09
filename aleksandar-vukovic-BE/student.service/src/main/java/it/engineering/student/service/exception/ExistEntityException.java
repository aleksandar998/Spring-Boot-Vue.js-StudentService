package it.engineering.student.service.exception;

public class ExistEntityException extends ApplicationException{
    private final Object entity;

    public ExistEntityException(Object entity, String message) {
        super(message);
        this.entity = entity;
    }

    public Object getEntity() {
        return entity;
    }
}
