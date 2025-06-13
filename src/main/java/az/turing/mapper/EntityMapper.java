package az.turing.mapper;

public interface EntityMapper<E,D,R> {
    E toEntityFromResponse(D d);
    D toDto(E e);
    R toRequest(E e);
    E toEntityFromRequest(R r);
}
