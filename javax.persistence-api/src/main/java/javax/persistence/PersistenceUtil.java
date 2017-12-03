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
package javax.persistence;

/**
 * アプリケーションと永続性ユニットの間のユーティリティインターフェースです。
 * 
 * <p> このインタフェースのメソッドは、この永続ユニットまたは新しいエンティティインスタンスのエンティティマネージャから取得または管理されたエンティティインスタンスに対してのみ呼び出す必要があります。
 * <code>PersistenceUtil</code>インターフェースのインスタンスは{@link Persistence}クラスから得られ、エンティティが作成された環境の永続性プロバイダに関係なくエンティティやエンティティの属性のロード状態を測定するために使用されます。
 *
 * @since Java Persistence 2.0
 */
public interface PersistenceUtil {

    /**
     * 与えられたエンティティの属性のロード状態を測定します。
     * @param entity  属性が含まれるエンティティ
     * @param attributeName ロード状態を測定される属性の名前
     * @return エンティティがロードされてない状態の場合や属性がロードされてない状態の場合はfalse、そうでない場合はtrue
     */
    public boolean isLoaded(Object entity, String attributeName);

    /**
     * 与えられたエンティティのロード状態を測定します。
     * 
     * このメソッドはリファレンスが渡されるエンティティのロード状態を測定するために使用できます。
     * <code>FetchType.EAGER</code>が指定されているすべての属性がロードされているときにエンティティはロードされているとみなされます。
     * <p> 属性のロード状態を測定するためには<code>isLoaded(Object, String)</code>メソッドを使用する必要があります。
     * そうしない場合、意図しない状態のロードにつながる可能性があります。
     * @param entity ロード状態を測定されるエンティティ
     * @return エンティティがロードされていない場合はfalse、そうでない場合はtrue
     */
    public boolean isLoaded(Object entity);
}
