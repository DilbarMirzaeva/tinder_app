package az.turing.mapper;

public interface EntityMapper<E,D> {
    E toEntity(D d);
    D toDto(E e);
}
