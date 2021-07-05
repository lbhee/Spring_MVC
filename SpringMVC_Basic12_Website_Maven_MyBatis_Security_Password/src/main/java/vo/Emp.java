package vo;

import lombok.Data;

/*
@Getter
@Setter
@AllArgsConstructor //오버로딩생성자(모든멤버필드를 파라미터로)
@ToString
@NoArgsConstructor //디폴트생성자
*/

@Data
public class Emp {
	private int empno;
	private String ename;
}
