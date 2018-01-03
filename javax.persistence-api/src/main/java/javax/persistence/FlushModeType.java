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
 * フラッシュモードの設定です。
 * 
 * <p> トランザクション内でクエリーが実行されるときに、
 * {@link javax.persistence.Query Query}や{@link javax.persistence.TypedQuery TypedQuery}のオブジェクトに<code>FlushModeType.AUTO</code>が設定されていた場合や、
 * 永続化コンテキストのフラッシュモード設定が<code>AUTO</code>(デフォルト)であり、<code>Query</code>や<code>TypedQuery</code>のオブジェクトにフラッシュモードの設定が指定されていない場合、
 * 永続化プロバイダは永続化コンテキスト内のすべてのエンティティの状態に対するすべての更新がクエリーの処理に潜在的に影響を与える可能性のあるものであるかどうかを確認する責任があります。 
 * 永続化プロバイダの実装では、これらのエンティティをデータベースにフラッシュするか、または他の手段でこれを果たせます。
 * 
 * <p> code>FlushModeType.COMMIT</code>が設定されている場合の照会時の永続コンテキスト内のエンティティに対する更新の効果は規定されていません。
 * 
 * <p> トランザクションがアクティブでないか、または永続化コンテキストが現在のトランザクションに参加していない場合、永続化プロバイダはデータベースにフラッシュしてはいけません。
 *
 * @since Java Persistence 1.0
 */
public enum FlushModeType {

    /**
     * フラッシュはトランザクションのコミット時に発生します。プロバイダはフラッシュを他の時に発生させることもできますが、
     * 必須ではありません。 
     */
   COMMIT,

    /** (デフォルト) フラッシュはクエリー実行時に発生します。 */
   AUTO
}
