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
 * <code>CriteriaQuery</code>インターフェイスはトップレベルのクエリーに固有の機能を定義します。
 *
 * @param <T>  定義された結果の型
 *
 * @since Java Persistence 2.0
 */
public interface CriteriaQuery<T> extends AbstractQuery<T> {
	
    /**
     * クエリーの結果に返される項目を指定します。
     * 
     * 以前に指定された項目があれば置き換えます。
     * 
     * <p>注意：文字列ベースのAPIを使用するアプリケーションでは、
     * 取得操作または結合操作の結果の項目でクエリーの結果の型が指定されている場合には、
     * 選択項目の型を指定する必要があります。
     *
     * <pre>
     *     例：
     *
     *     CriteriaQuery&#060;String&#062; q = cb.createQuery(String.class);
     *     Root&#060;Order&#062; order = q.from(Order.class);
     *     q.select(order.get("shippingAddress").&#060;String&#062;get("state"));
     * 
     *     CriteriaQuery&#060;Product&#062; q2 = cb.createQuery(Product.class);
     *     q2.select(q2.from(Order.class)
     *                 .join("items")
     *                 .&#060;Item,Product&#062;join("product"));
     * 
     * </pre>
     * @param selection  クエリーの結果に返される項目を指定するSelection
     * @return 変更されたクエリー
     * @throws IllegalArgumentException 選択項目が複合選択項目であり、複数の選択項目に同じ別名が割り当てられた場合
     */
    CriteriaQuery<T> select(Selection<? extends T> selection);

    /**
     * クエリーの結果から返される選択項目を指定します。
     * 
     * 以前に指定された項目があれば置き換えます。
     * クエリーの実行結果の型は、作成されたクライテリアクエリーオブジェクトの型の指定だけではなく<code>multiselect</code>メソッドの引数にも依存します。
     * <p> <code>multiselect</code>メソッドの引数はタプルや配列値の複合選択項目であってはなりません。
     *
     * <p>このメソッドのセマンティックスは以下のとおりです。: 
     * <ul> 
     * <li> 
     * If the type of the criteria query is
     * <code>CriteriaQuery&#060;Tuple&#062;</code> (i.e., a criteria
     * query object created by either the
     * <code>createTupleQuery</code> method or by passing a
     * <code>Tuple</code> class argument to the
     * <code>createQuery</code> method), a <code>Tuple</code> object
     * corresponding to the arguments of the <code>multiselect</code>
     * method, in the specified order, will be instantiated and
     * returned for each row that results from the query execution.
     *
     * <li> If the type of the criteria query is <code>CriteriaQuery&#060;X&#062;</code> for
     * some user-defined class X (i.e., a criteria query object
     * created by passing a X class argument to the <code>createQuery</code> 
     * method), the arguments to the <code>multiselect</code> method will be 
     * passed to the X constructor and an instance of type X will be 
     * returned for each row.  
     *
     * <li> If the type of the criteria query is <code>CriteriaQuery&#060;X[]&#062;</code> for
     * some class X, an instance of type X[] will be returned for 
     * each row.   The elements of the array will correspond to the 
     * arguments of the <code>multiselect</code> method, in the
     * specified order.  
     *
     * <li> If the type of the criteria query is <code>CriteriaQuery&#060;Object&#062;</code>
     * or if the criteria query was created without specifying a 
     * type, and only a single argument is passed to the <code>multiselect</code>
     * method, an instance of type <code>Object</code> will be returned for 
     * each row.
     *
     * <li> If the type of the criteria query is <code>CriteriaQuery&#060;Object&#062;</code>
     * or if the criteria query was created without specifying a 
     * type, and more than one argument is passed to the <code>multiselect</code>
     * method, an instance of type <code>Object[]</code> will be instantiated 
     * and returned for each row.  The elements of the array will 
     * correspond to the arguments to the <code> multiselect</code>  method,
     * in the specified order.
     * </ul>
     *
     * @param selections  クエリーによって返される結果に対応する選択項目
     * @return 変更されたクエリー
     * @throws IllegalArgumentException 選択項目が不正である、もしくは二つ以上の選択項目に同じエイリアスが指定されていた場合
     */
    CriteriaQuery<T> multiselect(Selection<?>... selections);


    /**
     * クエリーの結果から返される選択項目を指定します。
     * 
     * 以前に指定された項目があれば置き換えます。
     *
     * <p> The type of the result of the query execution depends on
     * the specification of the type of the criteria query object 
     * created as well as the argument to the <code>multiselect</code> method.
     * An element of the list passed to the <code>multiselect</code> method 
     * must not be a tuple- or array-valued compound selection item. 
     *
     * <p> The semantics of this method are as follows:
     * <ul>
     * <li> If the type of the criteria query is <code>CriteriaQuery&#060;Tuple&#062;</code>
     * (i.e., a criteria query object created by either the 
     * <code>createTupleQuery</code> method or by passing a <code>Tuple</code> class argument 
     * to the <code>createQuery</code> method), a <code>Tuple</code> object corresponding to 
     * the elements of the list passed to the <code>multiselect</code> method, 
     * in the specified order, will be instantiated and returned for each 
     * row that results from the query execution.
     *
     * <li> If the type of the criteria query is <code>CriteriaQuery&#060;X&#062;</code> for
     * some user-defined class X (i.e., a criteria query object
     * created by passing a X class argument to the <code>createQuery</code> 
     * method), the elements of the list passed to the <code>multiselect</code>
     * method will be passed to the X constructor and an instance
     * of type X will be returned for each row.  
     *
     * <li> If the type of the criteria query is <code>CriteriaQuery&#060;X[]&#062;</code> for
     * some class X, an instance of type X[] will be returned for 
     * each row.   The elements of the array will correspond to the 
     * elements of the list passed to the <code>multiselect</code> method,
     * in the specified order.  
     *
     * <li> If the type of the criteria query is <code>CriteriaQuery&#060;Object&#062;</code>
     * or if the criteria query was created without specifying a 
     * type, and the list passed to the <code>multiselect</code> method contains 
     * only a single element, an instance of type <code>Object</code> will be 
     * returned for each row.
     *
     * <li> If the type of the criteria query is <code>CriteriaQuery&#060;Object&#062;</code>
     * or if the criteria query was created without specifying a 
     * type, and the list passed to the <code>multiselect</code> method contains 
     * more than one element, an instance of type <code>Object[]</code> will be 
     * instantiated and returned for each row.  The elements of the 
     * array will correspond to the elements of the list passed to
     * the <code>multiselect</code> method, in the specified order.
     * </ul>
     *
     * @param selectionList  クエリーによって返される結果に対応する選択項目の一覧
     * @return 変更されたクエリー
     * @throws IllegalArgumentException 選択項目が不正である、もしくは二つ以上の選択項目に同じエイリアスが指定されていた場合
     */
    CriteriaQuery<T> multiselect(List<Selection<?>> selectionList);

    /**
     * 指定されたブール式に従ってクエリ結果を制限するようにクエリを変更します。
     * 
     * 以前に追加された制限があれば置き換えます。
     * このクエリーは対応する<code>AbstractQuery</code>のメソッドの結果型を上書きするだけです。
     * @param restriction  単純な、もしくは複合したブール式
     * @return 変更されたクエリー
     */
    CriteriaQuery<T> where(Expression<Boolean> restriction);

    /**
     * 指定されたブール式に従ってクエリ結果を制限するようにクエリを変更します。
     * 
     * 以前に追加された制限があれば置き換えます。
     * 制限が指定されていない場合は以前に追加された制限は単純に削除されます。
     * このクエリーは対応する<code>AbstractQuery</code>のメソッドの結果型を上書きするだけです。
     * @param restrictions  0個以上の制限の述語
     * @return 変更されたクエリー
     */
    CriteriaQuery<T> where(Predicate... restrictions);

    /**
     * クエリーの結果に対してグループを形成するために使用される式を指定します。
     * 
     * 以前に追加されたグループ化式があれば置き換えます。
     * グループ化式が指定されていない場合は以前に追加されたグループ化式は単純に削除されます。
     * このクエリーは対応する<code>AbstractQuery</code>のメソッドの結果型を上書きするだけです。
     * @param grouping  0個以上のグループ化式
     * @return 変更されたクエリー
     */
    CriteriaQuery<T> groupBy(Expression<?>... grouping);

    /**
     * クエリーの結果に対してグループを形成するために使用される式を指定します。
     * 
     * 以前に追加されたグループ化式があれば置き換えます。
     * グループ化式が指定されていない場合は以前に追加されたグループ化式は単純に削除されます。
     * このクエリーは対応する<code>AbstractQuery</code>のメソッドの結果型を上書きするだけです。
     * @param grouping  0個以上のグループ化式の一覧
     * @return 変更されたクエリー
     */
    CriteriaQuery<T> groupBy(List<Expression<?>> grouping);

    /**
     * クエリーのグループに対応する制限を指定します。
     * 
     * 以前に追加されたhaving制限があれば置き換えます。
     * このクエリーは対応する<code>AbstractQuery</code>のメソッドの結果型を上書きするだけです。
     * @param restriction  単純な、もしくは複合したブール式
     * @return 変更されたクエリー
     */
    CriteriaQuery<T> having(Expression<Boolean> restriction);

    /**
     * 指定された制限述語の結合に従ってクエリーのグループに対応する制限を指定します。
     *
     * 以前に追加されたhaving制限があれば置き換えます。
     * 制限が指定されていない場合は以前に追加された制限は単純に削除されます。
     * このクエリーは対応する<code>AbstractQuery</code>のメソッドの結果型を上書きするだけです。
     * @param restrictions  0個以上の制限の述語
     * @return 変更されたクエリー
     */
    CriteriaQuery<T> having(Predicate... restrictions);

    /**
     * クエリーの結果を順序付けするのに使用される順序付け式を指定します。
     * 
     * 以前に追加された順序付け式があれば置き換えます。
     * 順序付け式が指定されていない場合は以前に追加された順序付け式は単純に削除され、結果は不特定の順序で返されます。
     * 順序付け式の左から右の順序で優先順位が決定され、最も左の優先順位が最も高くなります。
     * @param o  0個以上の順序付け式
     * @return 変更されたクエリー
     */
    CriteriaQuery<T> orderBy(Order... o);

    /**
     * クエリーの結果を順序付けするのに使用される順序付け式を指定します。
     * 
     * 以前に追加された順序付け式があれば置き換えます。
     * 順序付け式が指定されていない場合は以前に追加された順序付け式は単純に削除され、結果は不特定の順序で返されます。
     * リスト内の順序付け式の順序によって優先順位が決定され、リスト内の最初の要素の優先順位が最も高くなります。
     * @param o  0個以上の順序付け式のList
     * @return 変更されたクエリー
     */
    CriteriaQuery<T> orderBy(List<Order> o);

    /**
     * 重複するクエリーの結果を除去するかどうかを指定します。
     * 
     * trueの値は重複を排除します。falseの値を指定すると重複が保持されます。
     * distinctが指定されていない場合は重複した結果を保持する必要があります。
     * このクエリーは対応する<code>AbstractQuery</code>のメソッドの結果型を上書きするだけです。
     * @param distinct  クエリーの結果から重複した結果を取り除くべきか、保持するべきかを指定するブール値
     * @return 変更されたクエリー
     */
    CriteriaQuery<T> distinct(boolean distinct);
    
    /**
     * 優先順位に従って順序付け式を返します。
     * 
     * 順序付け式が指定されていない場合は空のListを返します。
     * そのListへの変更はクエリーには影響を与えません。
     * @return 順序付け式のList
     */
    List<Order> getOrderList();
 
    /**
     * クエリーのパラメーターを返します。
     * 
     * パラメーターが存在しない場合は空のSetを返します。
     * そのSetへの変更はクエリーには影響を与えません。
     * @return クエリーのパラメーター
     */
    Set<ParameterExpression<?>> getParameters();
}
