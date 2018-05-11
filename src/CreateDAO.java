import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * ----------------------
 * Create my DAO Class.
 * Master Branch.
 * ----------------------
 */

/**
 * @author 006017
 *
 */
public class CreateDAO {

	/**
	 * @param args
	 */
	private static final String CLASSNAME = "CpbDocTypeMain";
	private static final String FILENAME_IDAO = 		"D:\\CreateClassDAO\\IDAO.txt";
	private static final String FILENAME_DAO = 			"D:\\CreateClassDAO\\DAO.txt";
	private static final String FILENAME_CLASS = 		"D:\\CreateClassDAO\\CLASS.txt";
	private static final String FILENAME_ROWMAPPER = 	"D:\\CreateClassDAO\\ROWMAPER.txt";
	private static final String DATATABLE = "CPB_DOC_TYPE_MAIN";
	//int , Integer , long , Long , String , Date
	// ,{"xxx","xxx","xxx"}
	private static final String daoProperties[][]  = {	{"dtMainId","String","DT_MAIN_ID"}
														,{"dtMainName","Date","DT_MAIN_NAME"}
														,{"createUser","Integer","CREATE_USER"}
														,{"createDatetime","Date","CREATE_DATETIME"}
														,{"updateUser","Integer","UPDATE_USER"}
														,{"updateDatetime","Date","UPDATE_DATETIME"}
														};
	
	public static void main(String[] args) {
		System.out.println("=== Begin create file. !!! ===");
		CreateDAO thisObj = new CreateDAO();
		thisObj.createPropertiesClass();
		thisObj.createInterfaceDAO();
		thisObj.createDAO();
		thisObj.createRowMapper();
		System.out.println("=== End create file. !!! ===");
	}
	
	private void createPropertiesClass() {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			File file = new File(FILENAME_CLASS);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file.getAbsoluteFile(), false);
			bw = new BufferedWriter(fw);
			//Begin set content.
			String content = "package com.gable.cpb.entity;\n";
			content += "\n";
			boolean chkImportDate = false;
			for (String[] properties : daoProperties) {
				if (!chkImportDate) {
					if (properties[1].equals("Date")) {
						content += "import java.util.Date;\n";
						chkImportDate = true;
					}
				}
			}
			content += "\n";
			content += "import com.fasterxml.jackson.annotation.JsonIgnore;\n";
			content += "import com.fasterxml.jackson.annotation.JsonIgnoreProperties;\n";
			content += "import com.fasterxml.jackson.annotation.JsonInclude;\n";
			content += "\n";
			content += "@JsonIgnoreProperties(ignoreUnknown = false)\n";
			content += "@JsonInclude(JsonInclude.Include.NON_NULL)\n";
			content += "public class "+CLASSNAME+" {\n";
			content += "\n";
			for (String[] properties : daoProperties) {
				content += "	private "+properties[1]+" "+properties[0]+";\n";
			}
			content += "\n";
			content += "	@JsonIgnore\n";
			content += "\n";
			//Begin Constructor.
			content += "	public "+CLASSNAME+"() {\n";
			content += "		super();\n";
			content += "	}\n";
			content += "\n";
			content += "	public "+CLASSNAME+"(";
			boolean firstTime = true;
			for (String[] property : daoProperties) {
				if (firstTime) {
					content += property[1]+" "+property[0];
					firstTime = false;
				} else {
					content += ", "+property[1]+" "+property[0];
				}
			}
			content += ") {\n";
			content += "		super();\n";
			for (String[] property : daoProperties) {
				content += "		this."+property[0]+" = "+property[0]+";\n";
			}
			content += "	}\n";
			//End Constructor.
			content += "\n";
			//Begin Get Set.
			for (String[] property : daoProperties) {
				content += "	public "+property[1]+" get"+property[0].substring(0, 1).toUpperCase() + property[0].substring(1)+"() {\n";
				content += "		return "+property[0]+";\n";
				content += "	}\n";
				content += "\n";
			}
			for (String[] property : daoProperties) {
				content += "	public void set"+property[0].substring(0, 1).toUpperCase() + property[0].substring(1)+"("+property[1]+" "+property[0]+") {\n";
				content += "		this."+property[0]+" = "+property[0]+";\n";
				content += "	}\n";
				content += "\n";
			}
			//End Get Set.
			content += "\n";
			//Begin To String.
			content += "	@Override\n";
			content += "	public String toString() {\n";
			content += "		return \""+CLASSNAME+" [";
			firstTime = true;
			for (String[] property : daoProperties) {
				if (firstTime) {
					content += property[0]+" = \" + "+property[0]+" + \"";
					firstTime = false;
				} else {
					content += ", "+property[0]+" = \" + "+property[0]+" + \"";
				}
			}
			content += "]\";\n";
			content += "	}\n";
			//End To String.
			content += "\n";
			content += "}";
			//End set content.
			bw.write(content);
			System.out.println("Property "+CLASSNAME+" Calss Done. !!!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void createInterfaceDAO() {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			File file = new File(FILENAME_IDAO);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file.getAbsoluteFile(), false);
			bw = new BufferedWriter(fw);
			//Begin set content.
			String content = "package com.gable.cpb.repository;\n";
			content += "\n";
			content += "import java.util.List;\n";
			content += "\n";
			content += "import com.gable.cpb.entity."+CLASSNAME+";\n";
			content += "\n";
			content += "public interface I"+CLASSNAME+"DAO {\n";
			content += "\n";
			content += "	List<"+CLASSNAME+"> getAll"+CLASSNAME+"s() throws Exception;\n";
			content += "\n";
			content += "}\n";
			//End set content.
			fw = new FileWriter(FILENAME_IDAO);
			bw = new BufferedWriter(fw);
			bw.write(content);
			System.out.println("Inteface "+CLASSNAME+" Class Done. !!!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private void createDAO() {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			File file = new File(FILENAME_DAO);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file.getAbsoluteFile(), false);
			bw = new BufferedWriter(fw);
			//Begin set content.
			String content = "package com.gable.cpb.repository;\n";
			content += "\n";
			content += "import java.util.ArrayList;\n";
			content += "import java.util.List;\n";
			content += "\n";
			content += "import org.slf4j.Logger;\n";
			content += "import org.slf4j.LoggerFactory;\n";
			content += "import org.springframework.beans.factory.annotation.Autowired;\n";
			content += "import org.springframework.beans.factory.annotation.Value;\n";
			content += "import org.springframework.context.annotation.PropertySource;\n";
			content += "import org.springframework.jdbc.core.BeanPropertyRowMapper;\n";
			content += "import org.springframework.jdbc.core.JdbcTemplate;\n";
			content += "import org.springframework.jdbc.core.RowMapper;\n";
			content += "import org.springframework.stereotype.Component;\n";
			content += "import org.springframework.stereotype.Repository;\n";
			content += "import org.springframework.transaction.annotation.Transactional;\n";
			content += "\n";
			content += "import com.gable.cpb.entity."+CLASSNAME+";\n";
			content += "import com.gable.cpb.entity."+CLASSNAME+"RowMapper;\n";
			content += "\n";
			content += "@Transactional\n";
			content += "@Repository\n";
			content += "@Component\n";
			content += "@PropertySource(\"classpath:application.properties\")\n";
			content += "public class "+CLASSNAME+"DAO implements I"+CLASSNAME+"DAO {\n";
			content += "\n";
			content += "	private static final Logger log = LoggerFactory.getLogger("+CLASSNAME+"DAO.class);\n";
			content += "\n";
			content += "	@Autowired\n";
			content += "	private JdbcTemplate jdbcTemplate;\n";
			content += "\n";
			content += "	@Value(\"${cpb.cmes.schema}\")\n";
			content += "	private String cmesSchema;\n";
			content += "\n";
			content += "	@Override\n";
			content += "	public List<"+CLASSNAME+"> getAll"+CLASSNAME+"s() throws Exception {\n";
			content += "		String sql = \"SELECT * FROM \"+cmesSchema+\""+DATATABLE+"\";\n";
			content += "		RowMapper<"+CLASSNAME+"> rowMapper = new "+CLASSNAME+"RowMapper();\n";
			content += "		List<"+CLASSNAME+"> result = new ArrayList<"+CLASSNAME+">();\n";
			content += "		try {\n";
			content += "			result = jdbcTemplate.query(sql, rowMapper);\n";
			content += "			log.info(\"(SUCCESS) Method getAll"+CLASSNAME+"s access database success.\");\n";
			content += "		} catch (Exception e) {\n";
			content += "			log.error(\"(ERROR) Method getAll"+CLASSNAME+"s RowMapper or JDBCTemplate error. : \"+e);\n";
			content += "			throw new Exception();\n";
			content += "		}\n";
			content += "		return result;\n";
			content += "	}\n";
			content += "\n";
			content += "}\n";
			//End set content.
			fw = new FileWriter(FILENAME_DAO);
			bw = new BufferedWriter(fw);
			bw.write(content);
			System.out.println("DAO "+CLASSNAME+" Class Done. !!!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private void createRowMapper() {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			File file = new File(FILENAME_ROWMAPPER);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file.getAbsoluteFile(), false);
			bw = new BufferedWriter(fw);
			//Begin set content.
			String content = "package com.gable.cpb.entity;\n";
			content += "\n";
			content += "import java.sql.ResultSet;\n";
			content += "import java.sql.SQLException;\n";
			content += "\n";
			content += "import org.springframework.jdbc.core.RowMapper;\n";
			content += "\n";
			content += "public class "+CLASSNAME+"RowMapper implements RowMapper<"+CLASSNAME+"> {\n";
			content += "\n";
			content += "	@Override\n";
			content += "	public "+CLASSNAME+" mapRow(ResultSet row, int rowNum) throws SQLException {\n";
			content += "		"+CLASSNAME+" entity = new "+CLASSNAME+"();\n";
			for (String[] property : daoProperties) {
				String propertyUppercase = property[0].substring(0, 1).toUpperCase() + property[0].substring(1);
				switch (property[1]) {
				case "int":
					content += "		entity.set"+propertyUppercase+"(row.getInt(\""+property[2]+"\"));\n";
					break;
				case "Integer":
					content += "		entity.set"+propertyUppercase+"(row.getInt(\""+property[2]+"\"));\n";
					break;
				case "long":
					content += "		entity.set"+propertyUppercase+"(row.getLong(\""+property[2]+"\"));\n";
					break;
				case "Long":
					content += "		entity.set"+propertyUppercase+"(row.getLong(\""+property[2]+"\"));\n";
					break;
				case "String":
					content += "		entity.set"+propertyUppercase+"(row.getString(\""+property[2]+"\"));\n";
					break;
				case "Date":
					content += "		entity.set"+propertyUppercase+"(row.getDate(\""+property[2]+"\"));\n";
					break;
				default:
					content += "		entity.set"+propertyUppercase+"(row.getString(\""+property[2]+"\"));\n";
					break;
				}
			}
			content += "		return entity;\n";
			content += "	}\n";
			content += "\n";
			content += "}\n";
			//End set content.
			fw = new FileWriter(FILENAME_ROWMAPPER);
			bw = new BufferedWriter(fw);
			bw.write(content);
			System.out.println("RowMapper "+CLASSNAME+" Class Done. !!!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
