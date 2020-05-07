/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swimming_competition;

/**
 *
 * @author Sutha
 */

public class Person {
    
    private String name_;
    private int age_;
    private String gender_;
    private String desination_;
    private String country_;
    
    Person(String name,int age,String gender,String desination,String country)
    {
       this.name_=name; 
       this.age_=age;
       this.gender_=gender;
       this.desination_=desination;
       this.country_=country;
    }
    
    public int get_age()
    {
        return age_;
    }
    
    public String get_country()
    {
        return country_;
    }
    
    public void set_name(String name)
    {
        this.name_=name;
    }
    
    public void set_age(int age)
    {
        this.age_=age;
    }
    
    public void set_gender(String gender)
    {
        this.gender_=gender;
    }
    
    public void set_desination(String desination)
    {
        this.desination_=desination;
    }

    public void set_country(String country)
    {
        this.country_=country;
    }
    
    public String get_name()
    {
        return name_;
    }
    
    public String get_desination()
    {
        return desination_;
    }
    
    public String get_gender()
    {
        return gender_;
    }
}
