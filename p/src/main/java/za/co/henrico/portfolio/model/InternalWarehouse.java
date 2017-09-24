package za.co.henrico.portfolio.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("INTERNAL")
@Entity
public class InternalWarehouse extends Warehouse {

}
