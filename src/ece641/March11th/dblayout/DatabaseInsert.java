package ece641.March11th.dblayout;


import ece641.March11th.entities.Contact;
import ece641.March11th.entities.User;
import ece641.March11th.entities.Data;

public interface DatabaseInsert {
public void addUser(User user);

public void addContact(Contact contact);

public void addData(Data event);

}
