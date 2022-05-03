package team3.OneSubscribe.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;
import team3.OneSubscribe.domain.Member;

import java.text.ParseException;
import java.util.Locale;

public class MemberConverter implements Converter<String, Member> {

    @Override
    public Member convert(String source) {
        return null;
    }

    @Override
    public <U> Converter<String, U> andThen(Converter<? super Member, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}
