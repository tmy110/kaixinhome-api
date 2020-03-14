package com.kaixin8848.home.web.favorites.service.impl;

import com.kaixin8848.home.utility.PageUtil;
import com.kaixin8848.home.utility.result.Result;
import com.kaixin8848.home.utility.result.ResultGenerator;
import com.kaixin8848.home.web.favorites.dao.FavoritesMapper;
import com.kaixin8848.home.web.favorites.model.Favorites;

import com.kaixin8848.home.core.AbstractService;
import com.kaixin8848.home.web.favorites.pojo.in.FavoritesCondition;
import com.kaixin8848.home.web.favorites.pojo.in.SortFavoritesCondition;
import com.kaixin8848.home.web.favorites.service.FavoritesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by tmy on 2019/12/08.
 */
@Service
@Transactional
public class FavoritesServiceImpl extends AbstractService<Favorites> implements FavoritesService {
    @Resource
    private FavoritesMapper favoritesMapper;

    /**
     * 查询收藏夹
     *
     * @param favoritesCondition
     * @return
     */
    @Override
    public List<Favorites> findFavorites(FavoritesCondition favoritesCondition) {
        return favoritesMapper.findFavorites(favoritesCondition);
    }

    /**
     * 排序收藏夹
     *
     * @param sortFavoritesConditions
     * @return
     */
    @Override
    public Result sortFavorites(List<SortFavoritesCondition> sortFavoritesConditions) {
        for (SortFavoritesCondition sortFavoritesCondition : sortFavoritesConditions) {
            Favorites favorites = new Favorites();
            favorites.setId(sortFavoritesCondition.getId());
            favorites.setSort(sortFavoritesCondition.getSort());
            favoritesMapper.updateByPrimaryKeySelective(favorites);
        }
        return ResultGenerator.genSuccessResult();
    }
}
