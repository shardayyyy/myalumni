package net.naijatek.myalumni.contacts;


import java.util.Date;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
import org.apache.commons.lang.builder.ToStringBuilder;
 
@Entity
@Table(name="CONTACTS")
public class Contact
{
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private int id;
 @Column private String name;
 @Column private String address;
 @Column private String gender;
 @Column private Date dob;
 @Column private String email;
 @Column private String mobile;
 @Column private String phone;
  
 @Override
 public String toString()
 {
  return ToStringBuilder.reflectionToString(this);
 }
 //setters & getters
}
