package com.example.tbea.common;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultTable<T> {

    // 响应状态码
    private int code;

    // 返回错误信息
    private String message;

    // 响应数据
    private Data<T> data;

    // Data 类定义
    public static class Data<T> {
        // 列表数据
        private List<T> list;

        // 总条目数
        private Integer total;

        // 每页显示条目个数
        private Integer pageSize;

        // 当前页数
        private Integer currentPage;

        // getters 和 setters
        public List<T> getList() {
            return list;
        }

        public void setList(List<T> list) {
            this.list = list;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public Integer getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(Integer currentPage) {
            this.currentPage = currentPage;
        }
    }


    // instance 方法
    public static <T> ResultTable<T> instance(int code, String message, List<T> list, Integer total, Integer pageSize, Integer currentPage) {
        Data<T> data = new Data<>();
        data.setList(list);
        data.setTotal(total);
        data.setPageSize(pageSize);
        data.setCurrentPage(currentPage);
        return new ResultTable<>(code, message, data);
    }


}
