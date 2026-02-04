package pe.andree.shopmanagerapi.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pe.andree.shopmanagerapi.dto.Meta;

@Component
public class MetaMapper {
    public Meta toMeta(Page<?> page) {
        return Meta.builder()
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }

    public Meta fromPage(Page<?> page) {
        return Meta.builder()
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }
}
