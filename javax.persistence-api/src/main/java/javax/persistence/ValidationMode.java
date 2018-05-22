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
 * 永続化ユニットのプロバイダによって使用される検証モードです。
 * 
 * @since Java Persistence 2.0
 */
public enum ValidationMode {
   
    /**
     * Bean Vdalidationプロバイダが環境により提供されている場合、永続化プロバイダは自動的にエンティティの検証を行わなければいけません。
     * Bean Validationプロバイダが環境から提供されていない場合、ライフサイクルイベントでの検証は行われません。
     * これはデフォルトの動作です。
     */
    AUTO,

    /**
     * 永続化プロバイダはライフサイクルイベントによる検証を行わなければいけません。Bean Validationプロバイダが環境から提供されてない場合はエラーになります。
     */
    CALLBACK,

    /**
     * 永続化プロバイダはライフサイクルイベントによる検証を行ってはいけません。
     */
    NONE
    }
