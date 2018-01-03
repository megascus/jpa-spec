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
 * 主キー生成戦略の型を定義します。
 *
 * @see GeneratedValue
 *
 * @since Java Persistence 1.0
 */
public enum GenerationType { 

    /**
     * 永続性プロバイダは一意性を保証するために基になるデータベースのテーブルを使用してエンティティの主キーに値を割り当てなければならないことを示します。
     */
    TABLE, 

    /**
     * 永続性プロバイダはデータベースのシーケンスを使用してエンティティの主キーに値を割り当てなければならないことを示します。
     */
    SEQUENCE, 

    /**
     * 永続性プロバイダはデータベースのID列を使用してエンティティの主キーに値を割り当てなければならないことを示します。
     */
    IDENTITY, 

    /**
     * 永続性プロバイダが特定のデータベースに対して適切な戦略を選択しなければならないことを示します。
     * 
     * <code>AUTO</code>生成戦略ではデータベースリソースが存在するか、生成されることが期待されます。
     * ベンダーはスキーマの生成をサポートしていない場合、または実行時にスキーマリソースを生成できない場合に、
     * そのようなリソースを生成する方法に関するドキュメントを提供することがあります。
     */
    AUTO
}
