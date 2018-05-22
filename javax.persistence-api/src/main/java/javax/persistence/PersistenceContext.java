/*******************************************************************************
 * Copyright (c) 2008 - 2017 Oracle Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Petros Splinakis - Java Persistence 2.2
 *     Linda DeMichiel - Java Persistence 2.1
 *     Linda DeMichiel - Java Persistence 2.0
 *
 ******************************************************************************/ 
package javax.persistence;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * コンテナ管理の{@link EntityManager}および関連する永続化コンテキストの依存関係を表現します。
 *
 * @since Java Persistence 1.0
 */

@Repeatable(PersistenceContexts.class)
@Target({TYPE, METHOD, FIELD})
@Retention(RUNTIME)
public @interface PersistenceContext {

    /**
     * (オプション) 環境参照のコンテキストでエンティティマネージャにアクセスする際の名前。DIを使用する場合は指定不要です。
     */
    String name() default "";

    /**
     * (オプション) <code>persistence.xml</code>に定義された永続化ユニットの名前。
     * 
     * <code>unitName</code>エレメントが指定されている場合、エンティティマネージャーのための永続化ユニットはJNDIで同じ名前でアクセスできなければなりません。
     */
    String unitName() default "";

    /**
     * (オプション) トランザクションスコープの永続化コンテキストもしくは拡張された永続化コンテキストのどちらを使用するか指定します。
     */
    PersistenceContextType type() default PersistenceContextType.TRANSACTION;

    /**
     * (オプション) 永続化コンテキストが常に現在のトランザクションと自動的に同期をするか、
     * 永続化コンテキストはEntityManagerの{@link EntityManager#joinTransaction joinTransaction}メソッドを使用して明示的にトランザクションに参加する必要があるかを指定します。
     * @since Java Persistence 2.1
     */
    SynchronizationType synchronization() default SynchronizationType.SYNCHRONIZED;

    /**
     * (オプション) コンテナや永続化プロバイダのためのプロパティー。
     * 
     * ベンダー固有のプロパティーはこのプロパティーのセットに含めることができます。
     * ベンダーによって認識されていないプロパティーは無視されます。
     */ 
    PersistenceProperty[] properties() default {};
}
