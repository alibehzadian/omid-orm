package info.behzadian;

import info.behzadian.repository.BranchRepository;
import info.behzadian.repository.UserRepository;
import info.behzadian.repository.entity.Branch;
import info.behzadian.repository.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

@SpringBootApplication
public class OmidOrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmidOrmApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(BranchRepository branchRepository, UserRepository userRepository) {
		return args -> {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String createBranchSchema = "CREATE TABLE IF NOT EXISTS BRANCH (ID MEDIUMINT NOT NULL AUTO_INCREMENT, BRANCH_NAME VARCHAR(64), ADDRESS VARCHAR(64), STATUS INTEGER, PRIMARY KEY (ID));";
				String createUserSchema = "CREATE TABLE IF NOT EXISTS USER (ID MEDIUMINT NOT NULL AUTO_INCREMENT, FIRST_NAME VARCHAR(64), LAST_NAME VARCHAR(64), USERNAME VARCHAR(64), PRIMARY KEY (ID)); ";
				String branchDataSql = "INSERT INTO BRANCH (BRANCH_NAME, ADDRESS, STATUS) VALUES ('Branch 1', 'Address 1', 0), ('Branch 2', 'Address 2', 1);";
				String userDataSql = "INSERT INTO USER (FIRST_NAME, LAST_NAME, USERNAME) VALUES ('Ali', 'Behzadian', 'behzadian'), ('Foo', 'Bar', 'foo.bar');";

				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/omid", "root", "pass");
				var stm = con.createStatement();
				stm.execute(createBranchSchema);
				stm.execute(branchDataSql);
				stm.execute(createUserSchema);
				stm.execute(userDataSql);

				List<Branch> branches = branchRepository.findAll();
				System.out.println("ALL BRANCHES:");
				for (Branch branch : branches) {
					System.out.println(branch);
				}
				System.out.println("\n\n");

				List<User> users = userRepository.findAll();
				System.out.println("ALL USERS:");
				for (User user : users) {
					System.out.println(user);
				}
				System.out.println("\n\n");

				System.out.println("Find By ID:");
				System.out.println(branchRepository.findById(1L));
				System.out.println(userRepository.findById(1L));
				System.out.println("\n\n");

				System.out.println("DELETE By ID:");
				branchRepository.deleteById(1L);
				userRepository.deleteById(1L);
				System.out.println(userRepository.findById(1L));
				System.out.println(branchRepository.findById(1L));
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}
}
