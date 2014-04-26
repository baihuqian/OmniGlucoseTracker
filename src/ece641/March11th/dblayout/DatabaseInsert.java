package ece641.March11th.dblayout;


import ece641.March11th.entities.Contact;
import ece641.March11th.entities.User;
import ece641.March11th.entities.Data;

public interface DatabaseInsert {
public int addUser(User user);

public int addContact(Contact contact);

public void addData(Data event);

}
