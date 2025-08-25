package com.simple_official_spring_template.api.input;

import com.simple_official_spring_template.dto.ProductDTO;

import java.util.List;

public class CategoryInput {
    private String[][] attributeFilters;
    private Integer id;
    private Integer page;
    private Integer limit;
    private int totalPages;
    private int totalItems;
    private List<ProductDTO> listResult;
    private String sort;

    public String[][] getAttributeFilters() {
        return attributeFilters;
    }

    public void setAttributeFilters(String[][] attributeFilters) {
        this.attributeFilters = attributeFilters;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public List<ProductDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<ProductDTO> listResult) {
        this.listResult = listResult;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
