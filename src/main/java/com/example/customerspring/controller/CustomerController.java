package com.example.customerspring.controller;
import com.example.customerspring.dto.ApiResponse;
import com.example.customerspring.dto.CustomerDto;
import com.example.customerspring.entity.Customer;
import com.example.customerspring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/customer")
public class CustomerController{
    @Autowired
    CustomerService customerService;
    @GetMapping
    public List<Customer> customersList(){
        List<Customer> customers = customerService.customerList();
        return customers;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable Integer id){
        Customer customerById = customerService.getCustomerById(id);
        return customerById;
    }
    @PostMapping
    public ApiResponse addCustomer(@RequestBody CustomerDto customerDTO){
        ApiResponse apiResponse = customerService.saveCustomer(customerDTO);
        return apiResponse;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteCustomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return "Ma'lumot O'chirildi";
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String update(@RequestBody CustomerDto customerDto,@PathVariable Integer id){
        customerService.updateCustomer(customerDto,id);
        return "Malumot Yangilandi";
    }
}