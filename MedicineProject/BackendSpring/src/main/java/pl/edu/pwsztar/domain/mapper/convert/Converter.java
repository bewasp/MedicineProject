package pl.edu.pwsztar.domain.mapper.convert;

public interface Converter<F, T> {
    T convert(F from);
}
