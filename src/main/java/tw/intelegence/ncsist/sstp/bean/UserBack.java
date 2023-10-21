package tw.intelegence.ncsist.sstp.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "users")
@Schema(name = "使用者", description = "使用者檔案")
public class UserBack implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Key", example = "0")
	private Long id;
	@Schema(description = "使用者名稱", example = "test1")
	private String username;
	@Schema(description = "使用者密碼", example = "0000")
	private String password;
	@Schema(description = "使用者別名", example = "測試者")
	private String name;
}
