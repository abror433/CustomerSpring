package com.example.customerspring.service;
import com.example.customerspring.dto.ApiResponse;
import com.example.customerspring.dto.CustomerDto;
import com.example.customerspring.entity.Customer;
import com.example.customerspring.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import java.util.Optional;
@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> customerList() {
        List<Customer> all = customerRepository.findAll();
        return all;
    }

    public Customer getCustomerById(Integer id) {
        Optional<Customer> byId = customerRepository.findById(id);
        Customer customer = byId.get();
        return customer;
    }

    public ApiResponse saveCustomer(CustomerDto customerDto) {
        boolean existsByPhone = customerRepository.existsByPhone(customerDto.getPhone());
        if (existsByPhone) {
            return new ApiResponse("Bunday Raqamli Customer Bor", false);
        }
        Customer customer = new Customer();
        customer.setAddres(customerDto.getAdres());
        customer.setName(customerDto.getName());
        customer.setPhone(customerDto.getPhone());
        customerRepository.save(customer);
        return new ApiResponse("Customer Saqlandi", true);
    }

    @RequestMapping(value = "/Customer/{id}", method = RequestMethod.DELETE)
    public String deleteCustomer(@PathVariable Integer id) {
        customerRepository.deleteById(id);
        return "Ma'lumot O'chirildi";
    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.PUT)
    public String updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable Integer id) {
        Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isPresent()) {
            Customer customer = optional.get();
            customer.setName(customerDto.getName());
            customer.setPhone(customerDto.getPhone());
            customer.setAddres(customerDto.getAdres());
            customerRepository.save(customer);
        }
        return "Malumot Yangilandi";
    }
}