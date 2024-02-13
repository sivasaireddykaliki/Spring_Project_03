package com.example.springproject_03.testhelper;

import com.example.springproject_03.entity.Address;
import com.example.springproject_03.entity.Company;
import com.example.springproject_03.entity.Geo;
import com.example.springproject_03.entity.User;

public class TestHelper {

    public String getUrl(String id)
    {
        String url="https://jsonplaceholder.typicode.com/users/";
        if(id != null)
        {
            return url+id;
        }
        else {
            return url;
        }
    }

    public User getDummyUser(String name)
    {

        User user = new User();
        user.setAddress(getDummyAddress());
        user.setName(name);
        user.setUsername("siva123");
        user.setEmail("anc@gmail.com");
        user.setPhone("1234567891");
        user.setWebsite("http://www.siva.in");
        user.setCompany(getDummyCompany());
        user.setId(6);
        return user;
    }



    public Address getDummyAddress()
    {

        Address address = new Address();
        Geo geo=getDummyGeo();
        address.setCity("Delhi");
        address.setGeo(geo);
        address.setStreet("ABC");
        address.setSuite("Unit A");
        address.setZipcode("11224");
        return address;

    }

    public Geo getDummyGeo()
    {
        Geo geo=new Geo();
        geo.setLat("11.22");
        geo.setLng("23.44");
        return geo;
    }

    public Company getDummyCompany()
    {
        Company company = new Company();
        company.setBs("ABC");
        company.setName("EHJ");
        company.setCatchPhrase("XYZ");
        return company;
    }
}
