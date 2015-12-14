package utils.mock;

/**
 * Created by vitaly on 14.12.15.
 */
public interface PageParser<T> {
    T parse(String pageContent);
}
