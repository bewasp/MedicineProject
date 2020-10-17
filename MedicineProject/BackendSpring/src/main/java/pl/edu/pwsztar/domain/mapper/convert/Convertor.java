package pl.edu.pwsztar.domain.mapper.convert;

public interface Convertor<F,T> {
    T convert(F from);
}
