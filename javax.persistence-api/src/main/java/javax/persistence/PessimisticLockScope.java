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
     * <p>永続化プロバイダはそのインスタンスのコレクションの値を除いた永続性状態に対応するデータベース行をロックするべきです。
     * テーブル結合をする継承ストラテジーが使用されている場合やエンティティがセカンダリーテーブルも使用してマッピングされている場合は、
     * 必然的に追加のテーブルの行をエンティティのインスタンスのためにロックします。
     * ロックされたエンティティの外部キーを含むエンティティのリレーションシップもロックされますが、参照されているエンティティの状態は(エンティティが明示的にロックされている場合を除いて)ロックされません。
     * エンティティが外部キーを含まない要素コレクションやリレーションシップ
     * (結合テーブルにマッピングされたリレーションシップや、ターゲットのエンティティが外部キーを含む一方向の一対多のリレーションシップなど)はデフォルトではロックされません。
     */
    NORMAL,

    /**
     * <code>javax.persistence.lock.scope</code>プロパティの値として<code>PessimisticLockScope.EXTENDED</code>が指定されている場合、
     * <code>PessimisticLockScope.NORMAL</code>の振る舞いに加えて要素コレクションやエンティティが所有する結合テーブルに含まれるリレーションシップはロックされます。
     * 
     * そのようなリレーションシップによって参照されるエンティティの状態は(エンティティが明示的にロックされている場合を除いて)ロックされません。
     * 一般に、そのようなリレーションシップや要素コレクションをロックすると、そのリレーションシップやコレクションの結合テーブルまたはコレクションテーブルの行のみがロックされます。
     * これはファントムリードが発生する可能性があることを意味します。
     * 
     * <p>訳注：{@code OneToMeny}等でエンティティの関連を作成すると、{@code JoinColumn}を指定しない場合は結合テーブル(中間テーブルと呼ばれることも多い)が作成されますが、中間テーブルまでしかロックされません。
     */
    EXTENDED
}
