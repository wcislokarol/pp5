package pl.wcislokarol.voucherstore.crm;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends CrudRepository<Client, Integer> {
}
