package problem;

import lombok.*;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestException extends RuntimeException {
    private String title;
    private String detail;
    private String message;
    private Map<String, Object> parameters;


    public static BadRequestException of(String title, String detail) {
        return BadRequestException.builder()
                .title(title)
                .detail(detail)
                .build();
    }


    public static BadRequestException of(String title, String detail, Object... data) {
        return BadRequestException.builder()
                .title(title)
                .detail(String.format(detail, data))
                .build();
    }

    public static BadRequestException of(String title, String detail, Map<String, Object> parameters, Object... data) {
        return BadRequestException.builder()
                .title(title)
                .parameters(parameters)
                .detail(String.format(detail, data))
                .build();
    }
}