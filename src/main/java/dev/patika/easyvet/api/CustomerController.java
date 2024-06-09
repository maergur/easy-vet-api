package dev.patika.easyvet.api;

import dev.patika.easyvet.business.abstracts.ICustomerService;
import dev.patika.easyvet.core.config.modelMapper.IModelMapperService;
import dev.patika.easyvet.core.exception.NotFoundException;
import dev.patika.easyvet.core.result.Result;
import dev.patika.easyvet.core.result.ResultData;
import dev.patika.easyvet.core.utilities.Msg;
import dev.patika.easyvet.core.utilities.ResultHelper;
import dev.patika.easyvet.dto.request.customer.CustomerSaveRequest;
import dev.patika.easyvet.dto.request.customer.CustomerUpdateRequest;
import dev.patika.easyvet.dto.response.CursorResponse;
import dev.patika.easyvet.dto.response.customer.CustomerResponse;
import dev.patika.easyvet.entities.Customer;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {
    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;

    public CustomerController(ICustomerService customerService, IModelMapperService modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest) {
        Customer saveCustomer = this.modelMapper.forRequest().map(customerSaveRequest, Customer.class);


        if (!customerService.existsByName(saveCustomer.getName())) {
            this.customerService.save(saveCustomer);
            return ResultHelper.created(this.modelMapper.forResponse().map(saveCustomer, CustomerResponse.class));
        } else {
            throw new NotFoundException(Msg.DUPLICATE_DATA);
        }
    }

    @GetMapping("/getByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> getName(@PathVariable("name") String name) {
        Customer customer = this.customerService.getByName(name);
        return ResultHelper.success(this.modelMapper.forResponse().map(customer, CustomerResponse.class));
    }

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> getId(@PathVariable("id") int id) {
        Customer customer = this.customerService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(customer, CustomerResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CustomerResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "100") int pageSize
    ) {
        Page<Customer> customerPage = this.customerService.cursor(page, pageSize);
        Page<CustomerResponse> customerResponsePage = customerPage
                .map(category -> this.modelMapper.forResponse().map(category, CustomerResponse.class));

        return ResultHelper.cursor(customerResponsePage);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        this.customerService.get(customerUpdateRequest.getId());
        Customer updateCustomer = this.modelMapper.forRequest().map(customerUpdateRequest, Customer.class);
        this.customerService.update(updateCustomer);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateCustomer, CustomerResponse.class));
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.customerService.delete(id);
        return ResultHelper.ok();
    }
}
