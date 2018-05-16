/*******************************************************************************
 * Copyright (c) 2008 - 2013 Oracle Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Linda DeMichiel - Java Persistence 2.1
 *     Linda DeMichiel - Java Persistence 2.0
 *
 ******************************************************************************/ 
package javax.persistence.criteria;

import java.util.List;
import java.util.Set;

/**
 * <code>Subquery</code>インターフェイスはサブクエリーに固有の機能を定義します。
 *
 * サブクエリーは選択項目として一つの式を持ちます。
 *
 * @param <T> 選択項目の型
 *
 * @since Java Persistence 2.0
 */
public interface Subquery<T> extends AbstractQuery<T>, Expression<T> {
	
    /**
     * サブクエリーの結果として返される項目を指定します。
     * 
     * 前に指定された選択項目があれば置き換えます。
     * @param expression  サブクエリーが返す項目を指定する式
     * @return 変更されたサブクエリー
     */
    Subquery<T> select(Expression<T> expression);
	
    /**
     * 指定されたブール式に従って結果を制限するようにサブクエリーを変更します。
     * 
     * 
     * 以前に追加された制限があれば置き換えます。
     * このクエリーは対応する<code>AbstractQuery</code>のメソッドの結果型を上書きするだけです。
     * @param restriction  単純な、もしくは複合したブール式
     * @return 変更されたサブクエリー
     */
    Subquery<T> where(Expression<Boolean> restriction);

    /**
     * 指定された制限述語の論理積に従って結果を制限するようにサブクエリを変更します。
     * 
     * 以前に追加された制限があれば置き換えます。
     * 制限が指定されていない場合は以前に追加された制限は単純に削除されます。
     * このクエリーは対応する<code>AbstractQuery</code>のメソッドの結果型を上書きするだけです。
     * @param restrictions  単純な、もしくは複合したブール式
     * @return 変更されたサブクエリー
     */
    Subquery<T> where(Predicate... restrictions);

    /**
     * サブクエリーの結果に対してグループを形成するために使用される式を指定します。
     * 
     * 以前に追加されたグループ化式があれば置き換えます。
     * グループ化式が指定されていない場合は以前に追加されたグループ化式は単純に削除されます。
     * このクエリーは対応する<code>AbstractQuery</code>のメソッドの結果型を上書きするだけです。
     * @param grouping  0個以上のグループ化式
     * @return 変更されたサブクエリー
     */
    Subquery<T> groupBy(Expression<?>... grouping);

    /**
     * サブクエリーの結果に対してグループを形成するために使用される式を指定します。
     * 
     * 以前に追加されたグループ化式があれば置き換えます。
     * グループ化式が指定されていない場合は以前に追加されたグループ化式は単純に削除されます。
     * このクエリーは対応する<code>AbstractQuery</code>のメソッドの結果型を上書きするだけです。
     * @param grouping  0個以上のグループ化式のリスト
     * @return 変更されたサブクエリー
     */
    Subquery<T> groupBy(List<Expression<?>> grouping);

    /**
     * サブクエリーのグループに対応する制限を指定します。
     * 
     * 以前に追加されたhaving制限があれば置き換えます。
     * このクエリーは対応する<code>AbstractQuery</code>のメソッドの結果型を上書きするだけです。
     * @param restriction  単純な、もしくは複合したブール式
     * @return 変更されたサブクエリー
     */
    Subquery<T> having(Expression<Boolean> restriction);

    /**
     * 指定された制限述語の結合に従ってサブクエリーのグループに対応する制限を指定します。
     *
     * 以前に追加されたhaving制限があれば置き換えます。
     * 制限が指定されていない場合は以前に追加された制限は単純に削除されます。
     * このクエリーは対応する<code>AbstractQuery</code>のメソッドの結果型を上書きするだけです。
     * @param restrictions  0個以上の制限の述語
     * @return 変更されたサブクエリー
     */
    Subquery<T> having(Predicate... restrictions);

    /**
     * 重複するクエリーの結果を除去するかどうかを指定します。
     * 
     * trueの値は重複を排除します。falseの値を指定すると重複が保持されます。
     * distinctが指定されていない場合は重複した結果を保持する必要があります。
     * このクエリーは対応する<code>AbstractQuery</code>のメソッドの結果型を上書きするだけです。
     * @param distinct  サブクエリーの結果から重複した結果を取り除くべきか、保持するべきかを指定するブール値
     * @return 変更されたサブクエリー.
     */
    Subquery<T> distinct(boolean distinct);
	
    /**
     * 囲んだクエリーのルートに相関したサブクエリーのルートを作ります。
     * @param parentRoot  含んだクエリーのルート
     * @return サブクエリーのルート
     */
    <Y> Root<Y> correlate(Root<Y> parentRoot);

    /**
     * Create a subquery join object correlated to a join object
     * of the enclosing query.
     * @param parentJoin  含んだクエリーの結合オブジェクト
     * @return サブクエリーの結合
     */
    <X, Y> Join<X, Y> correlate(Join<X, Y> parentJoin);

    /**
     * Create a subquery collection join object correlated to a 
     * collection join object of the enclosing query.
     * @param parentCollection  含んだクエリーの結合オブジェクト
     * @return サブクエリーの結合
     */
    <X, Y> CollectionJoin<X, Y> correlate(CollectionJoin<X, Y> parentCollection);

    /**
     * Create a subquery set join object correlated to a set join
     * object of the enclosing query.
     * @param parentSet  含んだクエリーの結合オブジェクト
     * @return サブクエリーの結合
     */
    <X, Y> SetJoin<X, Y> correlate(SetJoin<X, Y> parentSet);

    /**
     * Create a subquery list join object correlated to a list join
     * object of the enclosing query.
     * @param parentList 含んだクエリーの結合オブジェクト
     * @return サブクエリーの結合
     */
    <X, Y> ListJoin<X, Y> correlate(ListJoin<X, Y> parentList);

    /**
     * Create a subquery map join object correlated to a map join
     * object of the enclosing query.
     * @param parentMap 含んだクエリーの結合オブジェクト
     * @return サブクエリーの結合
     */
    <X, K, V> MapJoin<X, K, V> correlate(MapJoin<X, K, V> parentMap);

    /**
     * Return the query of which this is a subquery.
     * This must be a CriteriaQuery or a Subquery.
     * @return 囲んだクエリーもしくはサブクエリー
     */
    AbstractQuery<?> getParent();

    /**
     * Return the query of which this is a subquery.
     * This may be a CriteriaQuery, CriteriaUpdate, CriteriaDelete,
     * or a Subquery.
     * @return 囲んだクエリーもしくはサブクエリー
     * @since Java Persistence 2.1
     */
    CommonAbstractCriteria getContainingQuery();
	
    /**
     * 選択式を返します。
     * @return サブクエリーの結果として返される項目
     */
    Expression<T> getSelection();

    /**
     *  サブクエリーの相関結合を返します。
     * 
     *  サブクエリーが相関結合をもたない場合は空のセットを返します。
     *  セットへの変更はクエリーには影響を与えません。
     *  @return サブクエリーの相関結合
     */
    Set<Join<?, ?>> getCorrelatedJoins();

}

