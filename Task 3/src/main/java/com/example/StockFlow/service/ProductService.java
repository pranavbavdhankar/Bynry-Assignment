package com.example.StockFlow.service;

import com.example.StockFlow.dto.AddProductDTO;
import com.example.StockFlow.dto.ProductDTO;
import com.example.StockFlow.entity.Company;
import com.example.StockFlow.entity.Product;
import com.example.StockFlow.entity.Supplier;
import com.example.StockFlow.exception.ResourceAlReadyExistException;
import com.example.StockFlow.exception.ResourceNotFoundException;
import com.example.StockFlow.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CompanyService companyService;
    private final SupplierService supplierService;
    private final ProductSupplierService productSupplierService;

//    Get all products by company
    public Page<Product> getAllProductsByCompanyId(String companyId, Pageable pageable) {
        Company company = companyService.findById(companyId);
        return productRepository.findAllByCompany(company, pageable);
    }

//    Get Product By id
    public Product getProductById(String id) {
                return productRepository.findById(id)
                        .orElseThrow(() ->  new  ResourceNotFoundException(String.format("Product with Id : '%s' not found", id)));
    }

//    Create the new product
    @Transactional
    public Product createProduct(@Valid AddProductDTO dto) {

            boolean exist = this.existBySku(dto.getSku(), companyService.findById(dto.getCompanyId()));
            if(exist)
                throw new ResourceAlReadyExistException(String.format("SKU '%s' already exist", dto.getSku()));

            Product p = toProduct(dto);
            p = productRepository.save(p);

            for(String supplierId : dto.getSupplierId()){
                Supplier supplier = supplierService.getSupplierById( supplierId );
                productSupplierService.addProductSupplier(p, supplier);
            }

            return p;

    }

//    Check if product exists by SKU
    private boolean existBySku(String sku, Company company) {
        return productRepository.existsBySkuAndCompany(sku, company);
    }

//    Get the product By SKU
    private Product findBySku(String sku, String companyId) {
        return productRepository
                .findBySkuAndCompanyId(sku, companyId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Product with Sku : '%s' not found", sku)));
    }

//    Convert AddProductDTO to Product
    public Product toProduct(AddProductDTO dto) {
        Company company = companyService.findById(dto.getCompanyId());

        return Product.builder()
                .id(UUID.randomUUID().toString())
                .company(company)
                .sku(dto.getSku())
                .price(dto.getPrice())
                .name(dto.getName())
                .description(dto.getDescription())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }


//    Convert Product to ProductDTO
    public ProductDTO toDTO(Product p) {
        ProductDTO dto = new ProductDTO();
        dto.setId(p.getId());
        dto.setCompanyId(p.getCompany().getId());
        dto.setSku(p.getSku());
        dto.setPrice(p.getPrice());
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        dto.setCreatedAt(p.getCreatedAt());
        dto.setUpdatedAt(p.getUpdatedAt());
        return dto;
    }

}
