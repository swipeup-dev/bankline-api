package com.swipeupdev.banklineapi.configuration;

import com.swipeupdev.banklineapi.model.entity.Conta;
import com.swipeupdev.banklineapi.model.entity.Lancamento;
import com.swipeupdev.banklineapi.model.entity.PlanoConta;
import com.swipeupdev.banklineapi.model.entity.Usuario;
import com.swipeupdev.banklineapi.model.enums.TipoTransacao;
import com.swipeupdev.banklineapi.repository.ContaRepository;
import com.swipeupdev.banklineapi.repository.LancamentoRepository;
import com.swipeupdev.banklineapi.repository.PlanoContaRepository;
import com.swipeupdev.banklineapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TesteConfiguration implements CommandLineRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private PlanoContaRepository planoContaRepository;

    @Autowired
    private BCryptPasswordEncoder crypt;

    @Override
    public void run(String... args) throws Exception {
        Usuario u1 = new Usuario();
        u1.setCpf("12345678901");
        u1.setEmail("teste@teste.com");
        u1.setLogin("twsm");
        u1.setNome("Thomas");
        u1.setSenha(crypt.encode("123456789"));
        usuarioRepository.save(u1);

        Conta c1 = new Conta();
        c1.setUsuario(u1);
        contaRepository.save(c1);

        PlanoConta pc1 = newPlano("RECEITA", u1, true, TipoTransacao.ENTRADA);
        PlanoConta pc2 = newPlano("DESPESA", u1, true, TipoTransacao.SAIDA);
        PlanoConta pc3 = newPlano("TRANSFERÊNCIA ENTRE CONTAS", u1, true, TipoTransacao.SAIDA);
        PlanoConta pc4 = newPlano("TRANSFERÊNCIA ENTRE USUÁRIOS", u1, true, TipoTransacao.SAIDA);
        planoContaRepository.saveAll(Arrays.asList(pc1, pc2, pc3, pc4));

        Lancamento l1 = newLancamento(c1, "Salário", pc1, 1000);
        lancamentoRepository.save(l1);
        c1.setSaldo(c1.getSaldo() + l1.getValor());
        contaRepository.save(c1);

        Lancamento l2 = newLancamento(c1, "Luz", pc2, -249.90);
        lancamentoRepository.save(l2);
        c1.setSaldo(c1.getSaldo() + l2.getValor());
        contaRepository.save(c1);
    }

    private PlanoConta newPlano(String desc, Usuario user, boolean padrao, TipoTransacao tt) {
        PlanoConta pc = new PlanoConta();
        pc.setDescricao(desc);
        pc.setUsuario(user);
        pc.setPadrao(padrao);
        pc.setTipoTransacao(tt);

        return pc;
    }

    private Lancamento newLancamento(Conta c, String desc, PlanoConta pc, double valor) {
        Lancamento l1 = new Lancamento();
        l1.setConta(c);
        l1.setDataLancamento(Instant.now());
        l1.setDescricao(desc);
        l1.setPlanoConta(pc);
        l1.setValor(valor);
        return l1;
    }
}
