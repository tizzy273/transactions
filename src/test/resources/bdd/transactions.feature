Feature: Transactions

  Scenario: Create Transaction OK
    Given a Transaction with amount 100.0 and type 'DEPOSIT'
    When I call the Create Transaction endpoint
    Then the transaction should be created successfully

  Scenario: Create Transaction KO Withdrawal Too Big
    Given a Transaction with amount 1000000 and type 'WITHDRAWAL'
    When I call the Create Transaction endpoint
    Then I'll get HTTP Status Code 400 with message 'This account does not have enough funds to process a withdrawal of 1000000.0 €'

  Scenario: Create Transaction KO Wrong Value For Type
    Given a Transaction with amount 1000000 and type 'WRONG TYPE'
    When I call the Create Transaction endpoint
    Then I'll get HTTP Status Code 400 with message 'Transaction type accepts only:[DEPOSIT, WITHDRAWAL]'

  Scenario: Create Transaction KO Negative Value
    Given a Transaction with amount -1000000 and type 'DEPOSIT'
    When I call the Create Transaction endpoint
    Then I'll get HTTP Status Code 400 with message 'Your transaction cannot have a negative value'

  Scenario: Create Transaction KO NO Account id
    Given a Transaction with amount 1000000 and type 'DEPOSIT' and no Account Id specified
    When I call the Create Transaction endpoint
    Then I'll get HTTP Status Code 400 with message 'You have to define the account ID of your transaction'

  Scenario: Create Transaction KO NO Amount
    Given a Transaction with amount 1000000 and type 'DEPOSIT' and no Amount specified
    When I call the Create Transaction endpoint
    Then I'll get HTTP Status Code 400 with message 'You have to define an amount for your transaction'

  Scenario: Create Transaction KO NO Amount
    Given an instance of the DB where there are multiple Transactions
    When I call the Transaction History endpoint with AccountId param = 1
    Then I'll get all the transactions with AccountID = 1




