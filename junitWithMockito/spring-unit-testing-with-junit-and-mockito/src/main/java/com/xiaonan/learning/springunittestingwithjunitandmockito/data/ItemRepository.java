package com.xiaonan.learning.springunittestingwithjunitandmockito.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xiaonan.learning.springunittestingwithjunitandmockito.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{

}
