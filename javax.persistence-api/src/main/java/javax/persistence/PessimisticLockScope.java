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
 * 悲観ロックのための<code>javax.persistence.lock.scope</code>プロパティの値を定義します。
 * 
 * このプロパティは{@link EntityManager}や{@link Query}、{@link TypedQuery}インターフェースのメソッドでロックモードを指定するために引数として渡したり、
 * {@link NamedQuery}アノテーションと同時に使用することができます。
 *
 * @since Java Persistence 2.0
 */
public enum PessimisticLockScope {

    /**
     * この値は悲観ロックのデフォルトの動作を定義します。
     * 
     * <p>永続性プロバイダーはそのインスタンスのコレクションの値を除いた永続性状態に対応するデータベース行をロックするべきです。
     * 表結合をする継承ストラテジーが使用されている場合やエンティティがセカンダリーテーブルも使用してマッピングされている場合は、
     * 必然的に追加の表の行をエンティティのインスタンスのためにロックします。
     * ロックされたエンティティの外部キーを含む関連もロックされますが、参照されているエンティティの状態は(エンティティが明示的にロックされている場合を除いて)ロックされません。
     * エンティティが外部キーを含まない要素コレクションや関連
     * (結合テーブルにマッピングされた関連や、ターゲットのエンティティが外部キーを含む一方向の一対多関連など)はデフォルトではロックされません。
     */
    NORMAL,

    /**
     * In addition to the behavior for
     * <code>PessimisticLockScope.NORMAL</code>, element collections
     * and relationships owned by the entity that are contained in
     * join tables will be locked if the
     * <code>javax.persistence.lock.scope</code> property is specified
     * with a value of <code>PessimisticLockScope.EXTENDED</code>.
     * The state of entities referenced by such relationships will not
     * be locked (unless those entities are explicitly locked).
     * Locking such a relationship or element collection generally locks only
     * the rows in the join table or collection table for that
     * relationship or collection.  This means that phantoms will be
     * possible.
     */
    EXTENDED
}
