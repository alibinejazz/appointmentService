package appointment.peaceofmind.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import appointment.peaceofmind.Model.PriceLog;
import appointment.peaceofmind.Repository.PriceLogRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/price_log")
public class PriceLogController {
    
    @Autowired
    private PriceLogRepository pricelogrepo;

    @PostMapping("")
    public String postPriceLog(@RequestBody PriceLog pl){
        pricelogrepo.save(pl);
        return "gaya price log";
    }

    @GetMapping("")
    public List <PriceLog> getAllPriceLogs(){
        return pricelogrepo.findAll();
    } 

    @GetMapping("/get/{id}")
    public PriceLog getApriceLog(@PathVariable Long id){

       return pricelogrepo.findById(id).orElse(null);
    }

    @DeleteMapping("/deleteall")
    public String deleteAll(){
        pricelogrepo.deleteAll();
        return "sab urr gaye";
    }

}
