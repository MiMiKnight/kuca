package cn.mimiknight.developer.kuca.spring.appeasy.model;

import cn.mimiknight.developer.kuca.proto.api.errorcode.model.standard.IKucaErrorReturn;

public final class KucaERPPair extends KucaPair<String, IKucaErrorReturn> {

    public KucaERPPair(String left, IKucaErrorReturn right) {
        super(left, right);
    }
}
