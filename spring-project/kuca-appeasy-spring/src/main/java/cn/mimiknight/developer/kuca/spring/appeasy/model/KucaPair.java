package cn.mimiknight.developer.kuca.spring.appeasy.model;

import lombok.Getter;
import lombok.Setter;

/**
 * kuca pair
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2024-09-23 13:33:32
 */
@Getter
@Setter
public class KucaPair<L, R> {

    private L left;
    private R right;

    protected KucaPair(L left, R right) {
        this.left = left;
        this.right = right;
    }
}
