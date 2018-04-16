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
     * 削除クエリを変更して、指定されたブール式に従って削除する対象を制限します。
     * 
     * 以前に追加された制限があれば置き換えます。
     * @param restriction  単純な、もしくは複合したブール式
     * @return 変更された削除クエリー
     */    
   CriteriaDelete<T> where(Expression<Boolean> restriction);

    /**
     * 削除クエリを変更して、指定されたブール式に従って削除する対象を制限します。
     * 
     * 以前に追加された制限があれば置き換えます。
     * 制限が指定されていない場合は以前に追加された制限は単純に削除されます。
     * @param restrictions  0個以上の制限の述語
     * @return 変更された削除クエリー
     */
   CriteriaDelete<T> where(Predicate... restrictions);

}
