package org.test.endtoend.namesaver.entities;

import javax.persistence.*;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table
public class Names {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    private String forename;

    @NotEmpty
	private String surname;

	public Names() {
		// bean
	}

	public Names(String forename, String surname) {
		this.forename = forename;
		this.surname = surname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}
