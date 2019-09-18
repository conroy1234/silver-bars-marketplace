/*
 * order will be populated with spring data rest
 */

package com.silverbar.com.factory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.silverbar.com.entity.Order;
@Repository
public interface OrderRepository extends CrudRepository<Order, String> {

}
