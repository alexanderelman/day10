package edu.acc.java3.address;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddressDao implements java.io.Serializable {
    private final List<Address> addresses = new ArrayList<>();
    
    public List<Address> findAll() {
        return new ArrayList<>(addresses);
    }
    
    public Address findById(int id) {
        return new Address(addresses.get(id));
    }
    
    public void add(Address addr) throws IllegalArgumentException {
        validateState(addr);
        addresses.add(new Address(addr));
    }

    private void validateState(Address addr) throws IllegalArgumentException {
        if (addr.getState().equalsIgnoreCase("Iowa"))
            throw new IllegalArgumentException("We HATE Iowa");
    }
    
    public void remove(int[] ids) {
        List<Address> removeUs = new ArrayList<>();
        for (int i : ids)
            removeUs.add(addresses.get(i));
        addresses.removeAll(removeUs);
    }
    
    public void update(int id, Address replacement) throws IllegalArgumentException {
        validateState(replacement);
        addresses.set(id, replacement);
    }
}
