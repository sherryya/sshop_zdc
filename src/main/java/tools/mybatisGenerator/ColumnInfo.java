package tools.mybatisGenerator;

public class ColumnInfo {
	private int columnIndex;//序号，从0开始。
	private String columnName;	//小写
	private String columnLabel;	//小写
	private String columnJavaName;	//字段java对象的名字
	private String columnClassName;		//长类名，如java.long.String
	private String columnShortClassName;//短类名，如String。
	private String columnTypeName;
	private int columnType;
	private int columnDisplaySize;
	private int precision;
	private int scale;
	private boolean nullable;
	
	public int getColumnIndex() {
		return columnIndex;
	}
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnLabel() {
		return columnLabel;
	}
	public void setColumnLabel(String columnLabel) {
		this.columnLabel = columnLabel;
		if (!(columnLabel == null || columnLabel.equals(""))) {
			String upperColumnJavaName = MybatisGenerator.underline2ObjectName(columnLabel);
			columnJavaName = upperColumnJavaName.substring(0, 1).toLowerCase() + upperColumnJavaName.substring(1);;
		}
	}
	public String getColumnClassName() {
		return columnClassName;
	}
	public void setColumnClassName(String columnClassName) {
		this.columnClassName = columnClassName;
		
		//设置短类名
		int oldPos = 0, newPos = 0;
		while (newPos != -1) {
			newPos = columnClassName.indexOf('.', oldPos);
			if (newPos != -1) {
				oldPos = newPos + 1;
			}
		}
		columnShortClassName = columnClassName.substring(oldPos);
	}
	public String getColumnTypeName() {
		return columnTypeName;
	}
	public void setColumnTypeName(String columnTypeName) {
		this.columnTypeName = columnTypeName;
	}
	public int getColumnType() {
		return columnType;
	}
	public void setColumnType(int columnType) {
		this.columnType = columnType;
	}
	public int getColumnDisplaySize() {
		return columnDisplaySize;
	}
	public void setColumnDisplaySize(int columnDisplaySize) {
		this.columnDisplaySize = columnDisplaySize;
	}
	public int getPrecision() {
		return precision;
	}
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public boolean isNullable() {
		return nullable;
	}
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	public String getColumnShortClassName() {
		return columnShortClassName;
	}
	public void setColumnShortClassName(String columnShortClassName) {
		this.columnShortClassName = columnShortClassName;
	}
	public String getColumnJavaName() {
		return columnJavaName;
	}
	public void setColumnJavaName(String columnJavaName) {
		this.columnJavaName = columnJavaName;
	}
}
