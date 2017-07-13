package speed.model;

/**
 * Unit entity. @author MyEclipse Persistence Tools
 */

public class Unit implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String voiceFile;

	// Constructors

	/** default constructor */
	public Unit() {
	}

	/** minimal constructor */
	public Unit(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Unit(Integer id, String name, String voiceFile) {
		this.id = id;
		this.name = name;
		this.voiceFile = voiceFile;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVoiceFile() {
		return this.voiceFile;
	}

	public void setVoiceFile(String voiceFile) {
		this.voiceFile = voiceFile;
	}

}