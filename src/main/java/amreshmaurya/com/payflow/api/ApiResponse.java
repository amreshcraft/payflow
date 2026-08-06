package amreshmaurya.com.payflow.api;

import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
T data;
boolean success;
String message;
}
