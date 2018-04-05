/*******************************************************************************
 * Copyright (c) 2011 - 2013 Oracle Corporation. All rights reserved.
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
 *
 ******************************************************************************/ 
package javax.persistence.criteria;

import javax.persistence.metamodel.EntityType;

/**
 * <code>CriteriaDelete</code>インターフェイスはCriteria APIを使用して一括削除操作を実行するための機能を定義します。
 *
 * <p>Criteria APIの一括削除操作はデータベースの削除操作に直接対応します。
 * 永続化コンテキストは一括削除の結果と同期化されません。
 *
 * <p> <code>CriteriaDelete</code>オブジェクトは必ず単一のルートのみを持ちます。
 *
 * @param <T>  削除される対象のエンティティ型
 *
 * @since Java Persistence 2.1
 */
public interface CriteriaDelete<T> extends CommonAbstractCriteria {


    /**
     * 削除の対象となるエンティティに対応するクエリールートを作成して追加します。
     * 
     * <code>CriteriaDelete</code>オブジェクトは削除されるエンティティを示す単一のルートのみを持ちます。
     * @param entityClass  エンティティクラス
     * @return 与えられたエンティティに対応するクエリールート
     */
    Root<T> from(Class<T> entityClass);

    /**
     * 削除の対象となるエンティティに対応するクエリールートを作成して追加します。
     * 
     * <code>CriteriaDelete</code>オブジェクトは削除されるエンティティを示す単一のルートのみを持ちます。
     * @param entity  タイプ T のエンティティに対応するメタモデルエンティティ
     * @return 与えられたエンティティに対応するクエリールート
     */
    Root<T> from(EntityType<T> entity);

   /**
    * クエリールートを返します。
    * @return クエリールート
    */
   Root<T> getRoot();

    /**
     * Modify the delete query to restrict the target of the deletion 
     * according to the specified boolean expression.
     * Replaces the previously added restriction(s), if any.
     * @param restriction  a simple or compound boolean expression
     * @return the modified delete query
     */    
   CriteriaDelete<T> where(Expression<Boolean> restriction);

    /**
     * Modify the delete query to restrict the target of the deletion
     * according to the conjunction of the specified restriction 
     * predicates.
     * Replaces the previously added restriction(s), if any.
     * If no restrictions are specified, any previously added
     * restrictions are simply removed.
     * @param restrictions  zero or more restriction predicates
     * @return the modified delete query
     */
   CriteriaDelete<T> where(Predicate... restrictions);

}
