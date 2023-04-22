package code.with.me;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Iterable<Product> findAll() {
        return repository.findAll();
    }


}
