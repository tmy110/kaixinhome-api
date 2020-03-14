package com.kaixin8848.home.web.favorites.service;

import com.kaixin8848.home.utility.result.Result;
import com.kaixin8848.home.web.favorites.model.Favorites;
import com.kaixin8848.home.core.Service;
import com.kaixin8848.home.web.favorites.pojo.in.FavoritesCondition;
import com.kaixin8848.home.web.favorites.pojo.in.SortFavoritesCondition;

import java.util.List;


/**
 * Created by tmy on 2019/12/08.
 */
public interface FavoritesService extends Service<Favorites> {
    /**
     * 查询收藏夹
     *
     * @param favoritesCondition
     * @return
     */
    List<Favorites> findFavorites(FavoritesCondition favoritesCondition);

    /**
     * 排序收藏夹
     *
     * @param sortFavoritesConditions
     * @return
     */
    Result sortFavorites(List<SortFavoritesCondition> sortFavoritesConditions);
}
