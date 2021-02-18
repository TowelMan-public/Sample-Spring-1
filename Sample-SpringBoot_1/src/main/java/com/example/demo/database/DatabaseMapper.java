package com.example.demo.database;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.form.InsertUserForm;
import com.example.demo.form.LoginForm;

/*データベースの処理の部分
 * 環境に合わしてこの部分は作り変えてください。
 */

@Mapper
public interface DatabaseMapper {
	@Select("SELECT username,password FROM users WHERE username = #{username}")
	LoginForm getUserByUserName(String username);
	
	@Select("SELECT COUNT(*) = 0 FROM users WHERE username = #{username}")
	boolean isAbleInsertUsername(String username);
	
	@Insert("INSERT INTO users VALUES(#{username},#{password})")
	void insertUser(InsertUserForm form);
	
	@Update("UPDATE users SET username = #{after_username} , password = #{password} WHERE username = #{befor_username}")
	void updateUser(@Param("after_username") String afterUsername, @Param("password") String password, @Param("befor_username") String beforUsername);
	
	@Delete("DELETE FROM users WHERE username = #{username}")
	void deleteUser(String username);
}
